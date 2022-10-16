import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RentCalculatorComponent} from './rent-calculator/rent-calculator.component';
import {AddPaymentComponent} from './add-payment/add-payment-component';
import {PaymentDetailsComponent} from './payment-details/payment-details.component';
import {UpdatePaymentComponent} from './edit-payment/update-payment.component';
import {ConfigurationComponent} from './configuration/configuration.component';

const routes: Routes = [
  {path: 'rent-calculator', component: RentCalculatorComponent},
  {path: 'add-payment', component: AddPaymentComponent},
  {path: 'configuration', component: ConfigurationComponent},
  {path: 'edit-payment/:id', component: UpdatePaymentComponent},
  {path: 'payment-details/:id', component: PaymentDetailsComponent},
  {path: '', redirectTo: 'rent-calculator', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
