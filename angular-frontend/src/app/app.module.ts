import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule} from '@angular/forms';
import {AddPaymentComponent} from './add-payment/add-payment-component';
import {RentCalculatorComponent} from './rent-calculator/rent-calculator.component';
import {PaymentDetailsComponent} from './payment-details/payment-details.component';
import {UpdatePaymentComponent} from './edit-payment/update-payment.component';
import {ConfigurationComponent} from './configuration/configuration.component';

@NgModule({
  declarations: [
    AppComponent,
    RentCalculatorComponent,
    AddPaymentComponent,
    PaymentDetailsComponent,
    UpdatePaymentComponent,
    ConfigurationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
