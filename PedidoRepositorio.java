package lab_poo1.pedido_lab.repositorio;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import lab_poo1.pedido_lab.model.Pedido;

@Component
public class PedidoRepositorio 
{
    private List<Pedido> pedidos;

    @PostConstruct
    public void criarPedido()
    {
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();

        p1.setCodigo(1);
        p1.setDescricao("Pão com ovo");
        p1.setNome_cliente("Francisca");
        p1.setValor(20);
        p1.setDate(10_10);

        p2.setCodigo(2);
        p2.setDescricao("Bolo");
        p2.setNome_cliente("Pedro");
        p2.setValor(20.78);
        p2.setDate(10_12);

        p3.setCodigo(3);
        p3.setDescricao("Pão");
        p3.setNome_cliente("Ana");
        p3.setValor(78);
        p3.setDate(10_9);

        pedidos = new ArrayList<Pedido>();
        pedidos.add(p1);
        pedidos.add(p2);
        pedidos.add(p3);
    }

    public List<Pedido> getAllPedido()
    {
        return pedidos;
    }
    public Pedido getPedidoByCodigo(int codigo)
    {
        for(Pedido aux : pedidos)
        {
            if(aux.getCodigo() == codigo)
            {
                return aux;
            }
        }
        return null;
    }
    public Pedido save(Pedido pedido)
    {
        pedido.setCodigo(pedidos.size()+1);
        pedidos.add(pedido);
        return pedido;
    }

    public void remove(Pedido pedido) 
    {
        pedidos.remove(pedido);
	}

    public Pedido update(Pedido pedido)
    {
        Pedido aux = getPedidoByCodigo(pedido.getCodigo());
        if(aux != null)
        {
            aux.setDate(pedido.getDate());
            aux.setDescricao(pedido.getDescricao());
            aux.setNome_cliente(pedido.getNome_cliente());
            aux.setValor(pedido.getValor());
        }
        return aux;
	}
}
