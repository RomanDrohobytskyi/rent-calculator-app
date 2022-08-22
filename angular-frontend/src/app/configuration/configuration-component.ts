import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ConfigurationService} from './service/configuration-service';
import {RentPrice} from '../model/rent-price';
import {PaymentMessage} from '../model/payment-message';

@Component({
  selector: 'app-configuration-component',
  templateUrl: './configuration-component.html',
  styleUrls: ['./configuration-component.css']
})
export class ConfigurationComponent implements OnInit {

  rentPrice: RentPrice;
  paymentMessage: PaymentMessage;

  constructor(private configurationService: ConfigurationService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.configurationService.getPaymentMessage().subscribe(message => {
      this.paymentMessage = message;
      console.log(message);
      console.log(this.paymentMessage);
    });

    this.configurationService.getRentPrice().subscribe(rentPrice => {
      this.rentPrice = rentPrice;
    });
  }

  backup(): void {
    this.configurationService.doBackup().subscribe(data => {
        console.log(data);
        this.navigateToConfiguration();
      },
      error => console.log('Error occurred during backup: ' + error));
  }

  updateMessage(): void {
  }

  updateRentPrice(): void {
  }

  navigateToConfiguration(): void {
    this.router.navigate(['/configuration']);
  }
}
