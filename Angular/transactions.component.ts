import { Component } from '@angular/core';
import { CurrencyPipe, DatePipe } from '@angular/common';
@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [ CurrencyPipe, DatePipe],
  template: `
    <h2>Transactions</h2>
    <ul>
      @for (txn of transactions; track txn) {
        <li>
          {{ txn.date | date: 'longDate' }} -
          {{ txn.amount | currency: 'INR' : 'symbol' : '1.2-2' }}
        </li>
      }
    </ul>
    @if (transactions.length === 0) {
      <p>No transactions found.</p>
    }
  `,
})
export class TransactionsComponent {
  transactions = [
    { date: '2026-07-30', amount: 2000 },
    { date: '2026-07-31', amount: -500 },
  ];
}
