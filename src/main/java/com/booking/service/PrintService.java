package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showAllCustomer(List<Customer> customerList){
        System.out.println("\nTampilan Customer");
        System.out.println("No\tID\tNama\tAlamat\tMembership\tUang");
        int index = 1;
        for (Customer customer : customerList) {
            System.out.println(index + "\t" + customer.getId() + "\t" +
                    customer.getName() + "\t" +
                    customer.getAddress() + "\t" +
                    (customer.getMember() != null ? customer.getMember().getMembershipName() : "none") + "\t" +
                    customer.getWallet());
            index++;
        }
    }

    public void showAllEmployee(List<Employee> employeeList){
        System.out.println("\nTampilan Employee");
        System.out.println("No\tID\tNama\tAlamat\tPengalaman");
        int index = 1;
        for (Employee employee : employeeList) {
            System.out.println(index + "\t" + employee.getId() + "\t" +
                    employee.getName() + "\t" +
                    employee.getAddress() + "\t" +
                    employee.getExperience());
            index++;
        }
    }

    public void showHistoryReservation(){

    }

    public void showHistoryReservation(List<Reservation> reservationList) {
    }
}
