import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Payment} from '../model/payment';
import {PaymentService} from '../payments/payment-service';
import {UtilityBill, UtilityBillType} from '../model/utility-bill';

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
    this.payment.utilityBills = [this.initBill(UtilityBillType.WATER),
      this.initBill(UtilityBillType.GAS),
      this.initBill(UtilityBillType.ELECTRICITY)];
  }

  private initBill(type: UtilityBillType): UtilityBill {
    const utilityBill: UtilityBill = new UtilityBill();
    utilityBill.utilityType = type;
    utilityBill.meterState = 0;
    return utilityBill;
  }

  onSubmit(): void {
    this.addPayment();
  }

  addPayment(): void {
    this.payment.creationDate = new Date();
    this.paymentService.addPayment(this.payment).subscribe(() => {
        this.navigateToPaymentDetails();
      },
      error => console.log(error));
  }

  navigateToPaymentDetails(): void {
    this.router.navigate(['/rent-calculator']);
  }
}
