import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Payment} from '../model/payment';
import {PaymentService} from '../payments/payment-service';

@Component({
  selector: 'app-edit-payment',
  templateUrl: './edit-payment.component.html',
  styleUrls: ['./edit-payment.component.css']
})
export class EditPaymentComponent implements OnInit {

  id: number;
  payment: Payment = new Payment();
  loading = false;

  constructor(private paymentService: PaymentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;

    this.paymentService.getPaymentById(this.id).subscribe(data => {
      this.payment = data;
    }, error => console.log(error));
  }

  editPayment(): void {
    this.loading = true;
    this.paymentService.updatePayment(this.id, this.payment).subscribe(() => {
      this.loading = false;
      this.goToPayments();
    }, error => console.log(error));
  }

  recalculate(): void {
    this.loading = true;
    this.paymentService.recalculate(this.payment).subscribe(() => {
      this.loading = false;
      this.goToPayments();
    }, error => console.log(error));
  }

  goToPayments(): void {
    this.router.navigate(['/']);
  }
}
