package one.digitalinnovation.gof.service.imp;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser injetada pelo Spring via {@link Autowired}.
 * Com isso, como essa classe é um {@link Service}, ela será tratada como um <b>Singleton</b>.
 */
@Service
public class ClienteServiceImp implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Stratedy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        //  Buscar todos os clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        //  Buscar Cliente por Id.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente); // método definido no final do código
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()) {
            salvarClienteComCep(cliente); // método definido no final do código
        } else {
            System.out.println("Cliente inexistente.");
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID:
        clienteRepository.deleteById(id);

    }

    private void salvarClienteComCep(Cliente cliente) {    // aplicação do conceito de DRY (Dont Repeat Yourself)
        //  Verificar se o Endereço do Cliente já existe (pelo CEP):
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //  Caso não exista, integrar com o ViaCEP e persistir o retorno:
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereço (novo ou existente):
        clienteRepository.save(cliente);
    }
}
