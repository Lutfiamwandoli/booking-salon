package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Reservation> reservationList = new ArrayList<>();
    private static final List<Customer> customerList = new ArrayList<>(); // Anda perlu mengisi data customer
    private static final List<Employee> employeeList = new ArrayList<>(); // Anda perlu mengisi data employee
    private static final List<Service> serviceList = new ArrayList<>(); // Anda perlu mengisi data service

    public static void createReservation(){
        System.out.println("Membuat Reservasi");
        printCustomerList();
        System.out.print("Silahkan Masukkan Customer Id: ");
        String customerId = input.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer yang dicari tidak tersedia.");
            return;
        }

        printEmployeeList();
        System.out.print("Silahkan Masukkan Employee Id: ");
        String employeeId = input.nextLine();
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee yang dicari tidak tersedia.");
            return;
        }

        List<Service> selectedServices = selectServices();
        double totalReservationPrice = calculateReservationPrice(customer, selectedServices);

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setEmployee(employee);
        reservation.setServices(selectedServices);
        reservation.setReservationPrice(totalReservationPrice);
        reservation.setWorkstage("In Process");

        reservationList.add(reservation);

        System.out.println("Booking Berhasil!");
        System.out.println("Total Biaya Booking : Rp. " + totalReservationPrice);
    }

    public static void finishOrCancelReservation(){
        System.out.println("Finish/Cancel Reservasi");
        printReservationList();

        System.out.print("Silahkan Masukkan Reservation Id: ");
        String reservationId = input.nextLine();
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) {
            System.out.println("Reservation yang dicari tidak tersedia.");
            return;
        }

        System.out.print("Selesaikan reservasi (Finish/Cancel): ");
        String action = input.nextLine();
        if (action.equalsIgnoreCase("Finish")) {
            reservation.setWorkstage("Finish");
            System.out.println("Reservasi dengan id " + reservationId + " sudah Finish.");
        } else if (action.equalsIgnoreCase("Cancel")) {
            reservation.setWorkstage("Cancel");
            System.out.println("Reservasi dengan id " + reservationId + " sudah Cancel.");
        } else {
            System.out.println("Invalid action. Reservasi tidak diubah.");
        }
    }

    private static void printCustomerList() {
        System.out.println("\nList Customer:");
        for (Customer customer : customerList) {
            System.out.println(customer.getId() + "\t" + customer.getName());
        }
    }

    private static void printEmployeeList() {
        System.out.println("\nList Employee:");
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + "\t" + employee.getName());
        }
    }

    private static List<Service> selectServices() {
        List<Service> selectedServices = new ArrayList<>();
        String choice = null;
        do {
            printServiceList();
            System.out.print("Silahkan Masukkan Service Id: ");
            String serviceId = input.nextLine();
            Service service = findServiceById(serviceId);
            if (service == null) {
                System.out.println("Service yang dicari tidak tersedia.");
                continue;
            }
            if (selectedServices.contains(service)) {
                System.out.println("Service sudah dipilih.");
                continue;
            }
            selectedServices.add(service);
            System.out.print("Ingin pilih service yang lain (Y/T)? ");
            choice = input.nextLine();
        } while (choice.equalsIgnoreCase("Y"));
        return selectedServices;
    }

    private static void printServiceList() {
        System.out.println("\nList Service:");
        for (Service service : serviceList) {
            System.out.println(service.getServiceId() + "\t" + service.getServiceName());
        }
    }

    private static double calculateReservationPrice(Customer customer, List<Service> selectedServices) {
        double totalPrice = 0;
        for (Service service : selectedServices) {
            totalPrice += service.getPrice();
        }
        double discount = 0;
        if (customer.getMember() != null) {
            switch (customer.getMember().getMembershipName()) {
                case "Silver":
                    discount = 0.05;
                    break;
                case "Gold":
                    discount = 0.1;
                    break;
                default:
                    break;
            }
        }
        return totalPrice - (totalPrice * discount);
    }

    private static Customer findCustomerById(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private static Employee findEmployeeById(String employeeId) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    private static Service findServiceById(String serviceId) {
        for (Service service : serviceList) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    private static void printReservationList() {
        System.out.println("\nList Reservasi:");
        for (Reservation reservation : reservationList) {
            System.out.println(reservation.getReservationId() + "\t" +
                    reservation.getCustomer().getName() + "\t" +
                    reservation.getServices().toString() + "\t" +
                    reservation.getReservationPrice());
        }
    }

    private static Reservation findReservationById(String reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public static List<Reservation> getReservationList() {
        return null;
    }


    // Silakan tambahkan method lain sesuai kebutuhan
}
