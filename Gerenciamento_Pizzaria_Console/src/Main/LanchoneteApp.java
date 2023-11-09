package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanchoneteApp {
    private static final String ITENS_FILENAME = "itens.dat";
    private static final String VENDAS_FILENAME = "vendas.dat";

    public static void main(String[] args) {
        List<ItemDeVenda> itensDeVenda = loadItens();
        List<Venda> vendas = loadVendas();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar itens de venda");
            System.out.println("2. Atualizar itens de venda");
            System.out.println("3. Listar pizzas");
            System.out.println("4. Listar salgadinhos");
            System.out.println("5. Registrar vendas");
            System.out.println("6. Listar e gravar vendas em arquivo");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarItemDeVenda(itensDeVenda, scanner);
                    break;
                case 2:
                    atualizarItemDeVenda(itensDeVenda, scanner);
                    break;
                case 3:
                    listarPizzas(itensDeVenda);
                    break;
                case 4:
                    listarSalgadinhos(itensDeVenda);
                    break;
                case 5:
                    registrarVendas(vendas, itensDeVenda, scanner);
                    break;
                case 6:
                    listarEGravarVendas(vendas);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 7);
    }

    private static List<ItemDeVenda> loadItens() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ITENS_FILENAME))) {
            return (List<ItemDeVenda>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static List<Venda> loadVendas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VENDAS_FILENAME))) {
            return (List<Venda>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveItens(List<ItemDeVenda> itensDeVenda) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ITENS_FILENAME))) {
            oos.writeObject(itensDeVenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveVendas(List<Venda> vendas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VENDAS_FILENAME))) {
            oos.writeObject(vendas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarItemDeVenda(List<ItemDeVenda> itensDeVenda, Scanner scanner) {
        System.out.print("Nome do item: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do item: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Limpar a nova linha pendente

        ItemDeVenda item = new ItemDeVenda(nome, preco);
        itensDeVenda.add(item);
        saveItens(itensDeVenda);
        System.out.println("Item cadastrado com sucesso.");
    }

    private static void atualizarItemDeVenda(List<ItemDeVenda> itensDeVenda, Scanner scanner) {
        System.out.print("Nome do item a ser atualizado: ");
        String nome = scanner.nextLine();

        for (ItemDeVenda item : itensDeVenda) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                System.out.print("Novo nome (deixe em branco para manter o mesmo): ");
                String novoNome = scanner.nextLine();
                System.out.print("Novo preço (deixe 0 para manter o mesmo): ");
                double novoPreco = scanner.nextDouble();
                scanner.nextLine();  // Limpar a nova linha pendente

                if (!novoNome.isEmpty()) {
                    item.setNome(novoNome);
                }
                if (novoPreco != 0) {
                    item.setPreco(novoPreco);
                }

                saveItens(itensDeVenda);
                System.out.println("Dados atualizados com sucesso.");
                return;
            }
        }
        System.out.println("Item não encontrado.");
    }

    private static void listarPizzas(List<ItemDeVenda> itensDeVenda) {
        List<ItemDeVenda> pizzas = new ArrayList<>();
        for (ItemDeVenda item : itensDeVenda) {
            if (item.getNome().toLowerCase().contains("pizza")) {
                pizzas.add(item);
            }
        }

        if (!pizzas.isEmpty()) {
            pizzas.sort((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()));
            for (ItemDeVenda pizza : pizzas) {
                System.out.println(pizza.getNome() + ": R$ " + pizza.getPreco());
            }
        } else {
            System.out.println("Nenhuma pizza cadastrada.");
        }
    }

    private static void listarSalgadinhos(List<ItemDeVenda> itensDeVenda) {
        List<ItemDeVenda> salgadinhos = new ArrayList<>();
        for (ItemDeVenda item : itensDeVenda) {
            if (item.getNome().toLowerCase().contains("salgadinho")) {
                salgadinhos.add(item);
            }
        }

        if (!salgadinhos.isEmpty()) {
            salgadinhos.sort((s1, s2) -> Double.compare(s1.getPreco(), s2.getPreco()));
            for (ItemDeVenda salgadinho : salgadinhos) {
                System.out.println(salgadinho.getNome() + ": R$ " + salgadinho.getPreco());
            }
        } else {
            System.out.println("Nenhum salgadinho cadastrado.");
        }
    }

    private static void registrarVendas(List<Venda> vendas, List<ItemDeVenda> itensDeVenda, Scanner scanner) {
        System.out.print("Nome do item vendido (Digite 'sair' para encerrar): ");
        String nomeItem = scanner.nextLine();

        while (!nomeItem.equalsIgnoreCase("sair")) {
            System.out.print("Quantidade vendida: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();  // Limpar a nova linha pendente

            boolean encontrado = false;
            for (ItemDeVenda item : itensDeVenda) {
                if (item.getNome().equalsIgnoreCase(nomeItem)) {
                    vendas.add(new Venda(item.getNome(), quantidade));
                    saveVendas(vendas);
                    System.out.println("Venda registrada com sucesso.");
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Item não encontrado.");
            }

            System.out.print("Nome do item vendido (Digite 'sair' para encerrar): ");
            nomeItem = scanner.nextLine();
        }
    }

    private static void listarEGravarVendas(List<Venda> vendas) {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println("Item: " + venda.getNomeItem() + ", Quantidade: " + venda.getQuantidade());
            }
        }
    }
}