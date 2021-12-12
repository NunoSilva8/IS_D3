package book;

import entities.Currency;
import entities.Manager;
import entities.Retorno;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Boolean keepOnGoing = true;
        Scanner scanf = new Scanner(System.in);
        Integer opt = 0;

        Client client = ClientBuilder.newClient();

        System.out.println("------------------------------------------------------\n");
        System.out.println("\n" +
                "                     █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n" +
                "                     █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\n" +
                "                     █░░║║║╠─║─║─║║║║║╠─░░█\n" +
                "                     █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\n" +
                "                     ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n");
        System.out.println("------------------------------------------------------\n");

        do{
            System.out.println("Selecione a operação que deseja efetuar:\n\n");
            System.out.println("1. Adicionar um manager.");
            System.out.println("2. Adicionar um cliente.");
            System.out.println("3. Adicionar uma moeda.");
            System.out.println("4. Listar managers.");
            System.out.println("5. Listar clientes.");
            System.out.println("6. Listar moedas.");
            System.out.println("7. Listar créditos por cliente.");
            System.out.println("8. Listar pagamentos por cliente.");
            System.out.println("9. Imprimir o balance de um cliente.");
            System.out.println("10. Imprimir o total de cráditos efetuados.");
            System.out.println("11. Imprimir o total de pagamentos efetuados.");
            System.out.println("12. Imprimir o balance total.");
            System.out.println("13. Imprimir o recibo do último mês de um determinado cliente.");
            System.out.println("14. Listar os clientes sem pagamentos nos últimos 2 meses.");
            System.out.println("15. Imprimir o cliente com a maior dívida.");
            System.out.println("16. Imprimir os dados do manager com maior receita.");
            System.out.println("17. Sair.\n");

            System.out.print("Opção: ");
            opt = scanf.nextInt();
            System.out.println();


            optSwitch(opt, keepOnGoing, client, scanf) ;


        }while(keepOnGoing);

    }

    private static void optSwitch(Integer opt, Boolean keepOnGoing, Client client, Scanner scanf) {

        switch(opt){
            case 1:
                addManager(client, scanf);
                break;
            case 2:
                addClient(client, scanf);
                break;
            case 3:
                addCurrency(client, scanf);
                break;
            case 4:
                listManagers(client);
                break;
            case 5:
                listClients(client);
                break;
            case 6:
                listCurrencies(client);
                break;
            case 7:
                listCreditsPerClient(client);
                break;
            case 8:
                listPaymentsPerClient(client);
                break;
            case 9:
                getClientBalance(client);
                break;
            case 10:
                getTotalCredits(client);
                break;
            case 11:
                getTotalPayments(client);
                break;
            case 12:
                getTotalBalance(client);
                break;
            case 13:
                getClientLastMothBill(client);
                break;
            case 14:
                listClientsWithoutPurchasesInTheLastTwoMonths(client);
                break;
            case 15:
                getClientWithBiggestDebt(client);
                break;
            case 16:
                getManagerWithBiggestRevenue(client);
                break;
            case 17:
                keepOnGoing = false;
                break;
            default:
                break;
        }
        opt = 0;
    }

    private static void addManager(Client client, Scanner scanf) {
        String managerName;
        System.out.print("Insira o nome do manager: ");
        managerName = scanf.nextLine();

        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/add-manager");
        target = target.queryParam("managerName", "Picadasso");
        Response response = target.request().post(Entity.entity(new Manager(managerName), MediaType.APPLICATION_JSON));
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void addClient(Client client, Scanner scanf) {
        String clientName;
        Integer managerId;
        System.out.print("Insira o nome do cliente: ");
        clientName = scanf.nextLine();

        System.out.println("\nSelecione um manager para o cliente: ");
        listManagers(client);
        System.out.print("ManagerId: ");
        managerId = scanf.nextInt();

        WebTarget target = client.target("http://localhost:8080/restws/rest/client/add-client");
        target = target.queryParam("managerId", managerId);
        target = target.queryParam("clientName", clientName);

        Response response = target.request().post(
                Entity.entity(new entities.Client(clientName, new Manager(managerId, "")),
                MediaType.APPLICATION_JSON)
        );
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void addCurrency(Client client, Scanner scanf) {

        String currencyName;
        Long toEuro;
        System.out.print("Insira o nome da moeda: ");
        currencyName = scanf.nextLine();

        System.out.print("\nInsira o valor de troca para o Euro: ");
        toEuro = scanf.nextLong();

        WebTarget target = client.target("http://localhost:8080/restws/rest/currency/add-currency");
        target = target.queryParam("currencyName", "Dollar");
        target = target.queryParam("exchangeToEuro", 0.88);
        Response response = target.request().post(Entity.entity(
                new Currency(currencyName, toEuro),
                MediaType.APPLICATION_JSON)
        );
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listManagers(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-managers");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listClients(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-clients");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listCurrencies(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-currencies");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listCreditsPerClient(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-client-credit");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listPaymentsPerClient(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-client-payments");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getClientBalance(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-client-balance");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getTotalCredits(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-clients-credit");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getTotalPayments(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-clients-payments");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getTotalBalance(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-clients-balance");
        Response response = target.request().get();
        Retorno retorno = response.readEntity(Retorno.class);
        System.out.println(retorno.toString());
        response.close();
    }

    private static void getClientLastMothBill(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-client-bill-last-month");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void listClientsWithoutPurchasesInTheLastTwoMonths(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-clients-no-payments-last-two-months");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getClientWithBiggestDebt(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-client-in-most-debt");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }

    private static void getManagerWithBiggestRevenue(Client client) {
        WebTarget target = client.target("http://localhost:8080/restws/rest/manager/get-manager-most-revenue");
        Response response = target.request().get();
        String retorno = response.readEntity(String.class);
        System.out.println(retorno);
        response.close();
    }
}