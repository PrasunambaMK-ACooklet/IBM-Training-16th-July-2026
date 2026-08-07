import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BankingService } from './banking.service';

@Component({
  selector: 'app-transfer',
  standalone: true,
  imports: [FormsModule],
  template: `
    <h2>Fund Transfer</h2>
    <form (ngSubmit)="transfer()">
      <input [(ngModel)]="fromAccount" name="fromAccount" placeholder="From Account" />
      <input [(ngModel)]="toAccount" name="toAccount" placeholder="To Account" />
      <input [(ngModel)]="amount" name="amount" type="number" placeholder="Amount" />
      <button type="submit">Transfer</button>
    </form>

    @if (message) {
      <p>{{ message }}</p>
    }
  `,
})
export class TransferComponent {
  fromAccount = '';
  toAccount = '';
  amount = 0;
  message = '';

  constructor(private bankingService: BankingService) {}

  transfer() {
    this.bankingService
      .transferFunds({
        fromAccount: this.fromAccount,
        toAccount: this.toAccount,
        amount: this.amount,
      })
      .subscribe({
        next: () => (this.message = 'Transfer Successful!'),
        error: (err) => (this.message = 'Transfer Failed: ' + err.message),
      });
  }
}
