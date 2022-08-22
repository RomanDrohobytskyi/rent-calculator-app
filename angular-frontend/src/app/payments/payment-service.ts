import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Payment} from '../model/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private baseURL = 'http://localhost:8080/rent/calculator/payments';

  constructor(private httpClient: HttpClient) { }

  getPayments(): Observable<Payment[]> {
    return this.httpClient.get<Payment[]>(`${this.baseURL}`);
  }

  addPayment(payment: Payment): Observable<object> {
    return this.httpClient.post(`${this.baseURL}/add`, payment);
  }

  getPaymentById(id: number): Observable<Payment> {
    return this.httpClient.get<Payment>(`${this.baseURL}/details/${id}`);
  }

  archivePayment(id: number): Observable<Payment> {
    return this.httpClient.delete<Payment>(`${this.baseURL}/delete/${id}`);
  }

  updatePayment(id: number, payment: Payment): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/update/${id}`, payment);
  }

  /*
  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }*/
}
