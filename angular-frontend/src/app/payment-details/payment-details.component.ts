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

  id: number;
  payment: Payment;

  constructor(private route: ActivatedRoute,
              private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;

    this.payment = new Payment();
    this.paymentService.getPaymentById(this.id).subscribe( payment => {
      this.payment = payment;
    });
  }

}
