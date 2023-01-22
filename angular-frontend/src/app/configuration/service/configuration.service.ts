import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PaymentMessage} from '../../model/payment-message';
import {RentPrice} from '../../model/rent-price';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  private baseURL = 'http://localhost:8080/rent/calculator/configuration';

  constructor(private httpClient: HttpClient) { }

  doBackup(): Observable<object> {
    return this.httpClient.get<object>(`${this.baseURL}/backup`);
  }

  getPaymentMessage(): Observable<PaymentMessage> {
    return this.httpClient.get<PaymentMessage>(`${this.baseURL}/messages`);
  }

  getActualRentPrice(): Observable<RentPrice> {
    return this.httpClient.get<RentPrice>(`${this.baseURL}/price`);
  }
  /*
  getArchivalRentPrices(): Observable<RentPrice> {
    return this.httpClient.get<RentPrice>(`${this.baseURL}/price/archival`);
  }*/

  updatePaymentMessage(id: number, paymentMessage: PaymentMessage): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/update/messages/${id}`, paymentMessage);
  }

  updatePrice(rentPrice: RentPrice): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/price/update`, rentPrice);
  }

  /*  addPayment(payment: Payment): Observable<object> {
      return this.httpClient.post(`${this.baseURL}/add`, payment);
    }

    archivePayment(id: number): Observable<Payment> {
      return this.httpClient.delete<Payment>(`${this.baseURL}/delete/${id}`);
    }

    updatePayment(id: number, payment: Payment): Observable<object> {
      return this.httpClient.put(`${this.baseURL}/update/${id}`, payment);
    }*/

  /*
  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }*/
}
