package com.main.CrediLink.application.pix.job;

import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoClienteService agendamentoService;

    public AgendamentoController(AgendamentoClienteService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    // Criar agendamento
    @PostMapping("/cliente/{id}")
    public String agendar(@PathVariable String id,
                          @RequestParam int dia,
                          @RequestParam(defaultValue = "8") int hora) throws SchedulerException {
        agendamentoService.agendarConsultaMensal(id, dia, hora);
        return "✅ Agendamento criado para cliente " + id;
    }

    // Listar todos
    @GetMapping
    public List<Map<String, Object>> listar() throws SchedulerException {
        return agendamentoService.listarAgendamentos();
    }

    // Pausar
    @PostMapping("/cliente/{id}/pausar")
    public String pausar(@PathVariable String id) throws SchedulerException {
        agendamentoService.pausarAgendamento(id);
        return "⏸️ Agendamento pausado para cliente " + id;
    }

    // Retomar
    @PostMapping("/cliente/{id}/retomar")
    public String retomar(@PathVariable String id) throws SchedulerException {
        agendamentoService.retomarAgendamento(id);
        return "▶️ Agendamento retomado para cliente " + id;
    }

    // Excluir
    @DeleteMapping("/cliente/{id}")
    public String remover(@PathVariable String id) throws SchedulerException {
        agendamentoService.removerAgendamento(id);
        return "🗑️ Agendamento removido para cliente " + id;
    }
}

