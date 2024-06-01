package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;

import java.util.List;
import java.util.Scanner;

public class MenuService {
    private static final Scanner input = new Scanner(System.in);
    private static final PrintService printService = new PrintService();
    private static final ReservationService reservationService = new ReservationService();

    public static void mainMenu() {
        boolean backToMainMenu = false;
        do {
            System.out.println("\nMenu Awal");
            System.out.println("Aplikasi Booking Salon");
            System.out.println("1. Tampilkan Data");
            System.out.println("2. Membuat Reservasi");
            System.out.println("3. Finish/Cancel Reservasi");
            System.out.println("0. Exit");
            System.out.print("Pilihan Anda: ");
            int optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    showDataSubMenu();
                    break;
                case 2:
                    reservationService.createReservation();
                    break;
                case 3:
                    reservationService.finishOrCancelReservation();
                    break;
                case 0:
                    backToMainMenu = true;
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        } while (!backToMainMenu);
    }

    private static void showDataSubMenu() {
        boolean backToMainMenu = false;
        do {
            System.out.println("\nSub menu Tampilkan Data");
            System.out.println("Data Booking Salon");
            System.out.println("1. Tampilkan Recent Reservation");
            System.out.println("2. Tampilkan Customer");
            System.out.println("3. Tampilkan Employee");
            System.out.println("4. Tampilkan History Reservation + Total Keuntungan");
            System.out.println("0. Back To Main Menu");
            System.out.print("Pilihan Anda: ");
            int optionSubMenu = Integer.valueOf(input.nextLine());
            switch (optionSubMenu) {
                case 1:
                    printService.showRecentReservation(ReservationService.getReservationList());
                    break;
                case 2:
                    printService.showAllCustomer(Customer.getCustomerList());
                    break;
                case 3:
                    printService.showAllEmployee(EmployeeService.getEmployeeList());
                    break;
                case 4:
                    printService.showHistoryReservation(ReservationService.getReservationList());
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        } while (!backToMainMenu);
    }

    private static class EmployeeService {
        public static List<Employee> getEmployeeList() {
            return null;
        }
    }
}
