import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PaymentService} from '../payments/payment-service';
import {Payment} from '../model/payment';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.css']
})
export class PaymentDetailsComponent implements OnInit {

  payment: Payment;

  constructor(private route: ActivatedRoute,
              private paymentService: PaymentService) { }

  ngOnInit(): void {
    const paymentId = this.route.snapshot.params.id;
    this.paymentService.getPaymentById(paymentId).subscribe(payment => {
      this.payment = payment;
    });
  }

}
