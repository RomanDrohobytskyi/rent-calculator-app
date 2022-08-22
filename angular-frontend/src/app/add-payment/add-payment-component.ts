import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Payment} from '../model/payment';
import {PaymentService} from '../payments/payment-service';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment-component.html',
  styleUrls: ['./add-payment-component.css']
})
export class AddPaymentComponent implements OnInit {

  payment: Payment = new Payment();

  constructor(private paymentService: PaymentService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.addPayment();
  }

  addPayment(): void {
    this.payment.creationDate = new Date();
    this.paymentService.addPayment(this.payment).subscribe(data => {
        console.log(data);
        this.navigateToPaymentDetails();
      },
      error => console.log(error));
  }

  navigateToPaymentDetails(): void {
    this.router.navigate(['/rent-calculator']);
  }
}
