package com.main.CrediLink.application.pix.job;

import com.main.CrediLink.application.config.PixExpirationJob;
import org.quartz.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AgendamentoClienteService {

    private final Scheduler scheduler;
    private final JdbcTemplate jdbcTemplate;

    public AgendamentoClienteService(Scheduler scheduler, JdbcTemplate jdbcTemplate) {
        this.scheduler = scheduler;
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔹 Criar novo job
    public void agendarConsultaMensal(String clienteId, int diaDoMes, int hora) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("jobCliente-" + clienteId, "consultas");

        if (scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Já existe um agendamento para o cliente " + clienteId);
        }

        JobDetail jobDetail = JobBuilder.newJob(PixExpirationJob.class)
                .withIdentity(jobKey)
                .usingJobData("clienteId", clienteId)
                .build();

        String cron = String.format("0 0 %d %d * ?", hora, diaDoMes);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerCliente-" + clienteId, "consultas")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public List<Map<String, Object>> listarAgendamentos() {
        String sql = """
            SELECT 
                jd.job_name,
                jd.job_group,
                t.trigger_name,
                t.trigger_group,
                t.next_fire_time,
                t.prev_fire_time,
                t.trigger_state
            FROM qrtz_job_details jd
            JOIN qrtz_triggers t
              ON jd.job_name = t.job_name
             AND jd.job_group = t.job_group
            WHERE jd.job_name LIKE 'jobCliente-%'
        """;

        return jdbcTemplate.queryForList(sql)
                .stream()
                .map(row -> Map.of(
                        "clienteId", ((String) row.get("job_name")).replace("jobCliente-", ""),
                        "proximaExecucao", row.get("next_fire_time"),
                        "ultimaExecucao", row.get("prev_fire_time"),
                        "estado", row.get("trigger_state")
                ))
                .toList();
    }

    // 🔹 Pausar agendamento
    public void pausarAgendamento(String clienteId) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("jobCliente-" + clienteId, "consultas"));
    }

    // 🔹 Retomar agendamento pausado
    public void retomarAgendamento(String clienteId) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey("jobCliente-" + clienteId, "consultas"));
    }

    // 🔹 Remover agendamento
    public void removerAgendamento(String clienteId) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey("jobCliente-" + clienteId, "consultas"));
    }
}




