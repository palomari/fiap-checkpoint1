package br.com.fiap.checkpoint1.service;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(@Valid Pedido pedido) {
        if (pedido.getPrato() == null || pedido.getPrato().isBlank()) {
            throw new ValidationException("O prato é obrigatório");
        }

        if (pedido.getValor() < 0) {
            throw new ValidationException("O valor não pode ser negativo");
        }

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, @Valid Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setPrato(pedidoAtualizado.getPrato());
            pedido.setValor(pedidoAtualizado.getValor());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
