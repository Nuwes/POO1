package lab_poo1.pedido_lab.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lab_poo1.pedido_lab.model.Pedido;
import lab_poo1.pedido_lab.repositorio.PedidoRepositorio;

@RestController
@RequestMapping("/pedido")
public class PedidoController
{
    @Autowired
    private PedidoRepositorio repositorio;

    @GetMapping
    public List <Pedido> getPedido()
    {
        return repositorio.getAllPedido();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo)
    {
        Pedido pedido = repositorio.getPedidoByCodigo(codigo);

        if(pedido != null)
        {
            repositorio.remove(pedido);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido, @PathVariable int codigo)
    {
        if(repositorio.getPedidoByCodigo(codigo) != null)
        {
            pedido.setCodigo(codigo);
            pedido = repositorio.update(pedido);
            return ResponseEntity.ok(pedido);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int codigo)
    {
        Pedido pedido = repositorio.getPedidoByCodigo(codigo);

        if(pedido != null)
        {
            return ResponseEntity.ok(pedido);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedidos)
    {
        Pedido novoPedido = repositorio.save(pedidos);
        URI uri = URI.create("http://localhost:8080/pedido/" + novoPedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }
}
