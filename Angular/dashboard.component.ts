import { Component } from '@angular/core';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CurrencyPipe],
  template: `
    <h2>Account Dashboard</h2>
    <p>Balance: {{ balance | currency: 'INR' }}</p>
  `,
})
export class DashboardComponent {
  balance: number = 5000;
}
