package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

import java.util.List;

public class ValidationService {

    public static boolean validateCustomerId(String customerId, List<Customer> customerList) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return true; // Customer ditemukan
            }
        }
        return false; // Customer tidak ditemukan
    }

    public static boolean validateEmployeeId(String employeeId, List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(employeeId)) {
                return true; // Employee ditemukan
            }
        }
        return false; // Employee tidak ditemukan
    }

    public static boolean validateServiceId(String serviceId, List<Service> serviceList) {
        for (Service service : serviceList) {
            if (service.getServiceId().equals(serviceId)) {
                return true; // Service ditemukan
            }
        }
        return false; // Service tidak ditemukan
    }

    public static boolean validateReservationId(String reservationId, List<Reservation> reservationList) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                return true; // Reservation ditemukan
            }
        }
        return false; // Reservation tidak ditemukan
    }

    // Silakan tambahkan method lain sesuai kebutuhan
}
