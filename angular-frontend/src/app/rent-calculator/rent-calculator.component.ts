import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Payment} from '../model/payment';
import {PaymentService} from '../payments/payment-service';

@Component({
  selector: 'app-payments',
  templateUrl: './rent-calculator.component.html',
  styleUrls: ['./rent-calculator.component.css']
})
export class RentCalculatorComponent implements OnInit {

  payments: Payment[];

  constructor(private paymentService: PaymentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPayments();
  }

  private getPayments(): void {
    this.paymentService.getPayments().subscribe(data => {
      this.payments = data;
    });
  }

  paymentDetails(id: number): void {
    this.router.navigate(['payment-details', id]);
  }

  updatePayment(id: number): void {
    this.router.navigate(['edit-payment', id]);
  }

  archivePayment(id: number): void {
    this.paymentService.archivePayment(id).subscribe(data => {
      console.log(data);
      this.getPayments();
    });
  }
}
