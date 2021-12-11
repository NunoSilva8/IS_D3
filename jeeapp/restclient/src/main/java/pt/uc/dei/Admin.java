package pt.uc.dei;

import java.util.Scanner;

public class Admin {
    public static void main(String[] args) throws Exception {
        Boolean keepOnGoing = true;
        Scanner scanf = new Scanner(System.in);
        Integer opt = 0;

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
            System.out.println("1. Adicionar um manager.\n");
            System.out.println("2. Adicionar um cliente.\n");
            System.out.println("3. Adicionar uma moeda.\n");
            System.out.println("4. Listar managers.\n");
            System.out.println("5. Listar clientes.\n");
            System.out.println("6. Listar moedas.\n");
            System.out.println("7. Listar créditos por cliente.\n");
            System.out.println("8. Listar pagamentos por cliente.\n");
            System.out.println("9. Imprimir o balance de um cliente.\n");
            System.out.println("10. Imprimir o total de cráditos efetuados.\n");
            System.out.println("11. Imprimir o total de pagamentos efetuados.\n");
            System.out.println("12. Imprimir o balance total.\n");
            System.out.println("13. Imprimir o recibo do último mês de um determinado cliente.\n");
            System.out.println("14. Listar os clientes sem pagamentos nos últimos 2 meses.\n");
            System.out.println("15. Imprimir o cliente com a maior dívida.\n");
            System.out.println("16. Imprimir os dados do manager com maior receita.\n");
            System.out.println("17. Sair.\n\n\n");

            System.out.print("Opção: ");
            opt = scanf.nextInt();
            System.out.println();


            optSwitch(opt, keepOnGoing);


        }while(keepOnGoing);

    }

    private static void optSwitch(Integer opt, Boolean keepOnGoing) {

        switch(opt){
            case 1:
                addManager();
                break;
            case 2:
                addClient();
                break;
            case 3:
                addCurrency();
                break;
            case 4:
                listManagers();
                break;
            case 5:
                listClients();
                break;
            case 6:
                listCurrencies();
                break;
            case 7:
                listCreditsPerClient();
                break;
            case 8:
                listPaymentsPerClient();
                break;
            case 9:
                getClientBalance();
                break;
            case 10:
                getTotalCredits();
                break;
            case 11:
                getTotalPayments();
                break;
            case 12:
                getTotalBalance();
                break;
            case 13:
                getClientLastMothBill();
                break;
            case 14:
                listClientsWithoutPurchasesInTheLastTwoMonths();
                break;
            case 15:
                getClientWithBiggestDebt();
                break;
            case 16:
                getManagerWithBiggestRevenue();
                break;
            case 17:
                keepOnGoing = false;
                break;
            default:
                break;
        }
        opt = 0;
    }

    private static void addManager() {
    }

    private static void addClient() {
    }

    private static void addCurrency() {
    }

    private static void listManagers() {
    }

    private static void listClients() {
    }

    private static void listCurrencies() {
    }

    private static void listCreditsPerClient() {
    }

    private static void listPaymentsPerClient() {
    }

    private static void getClientBalance() {
    }

    private static void getTotalCredits() {
    }

    private static void getTotalPayments() {
    }

    private static void getTotalBalance() {
    }

    private static void getClientLastMothBill() {
    }

    private static void listClientsWithoutPurchasesInTheLastTwoMonths() {
    }

    private static void getClientWithBiggestDebt() {
    }

    private static void getManagerWithBiggestRevenue() {
    }
}