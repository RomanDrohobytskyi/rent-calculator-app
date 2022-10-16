import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Payment} from '../model/payment';
import {PaymentService} from '../payments/payment-service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-payment.component.html',
  styleUrls: ['./update-payment.component.css']
})
export class UpdatePaymentComponent implements OnInit {

  id: number;
  payment: Payment = new Payment();

  constructor(private paymentService: PaymentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;

    this.paymentService.getPaymentById(this.id).subscribe(data => {
      this.payment = data;
    }, error => console.log(error));
  }

  updatePayment(): void {
    this.paymentService.updatePayment(this.id, this.payment).subscribe( () => {
        this.goToPayments();
      }
      , error => console.log(error));
  }

  recalculate(): void {
    this.paymentService.recalculate(this.payment).subscribe( () => {
        this.goToPayments();
      }
      , error => console.log(error));
  }

  goToPayments(): void{
    this.router.navigate(['/']);
  }
}
