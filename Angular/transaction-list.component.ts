import { Component } from '@angular/core';

@Component({
  selector: 'app-transaction-list',
  template: `
    <h3>Transaction List</h3>
    <p>Transactions will be displayed here.</p>
  `
})
export class TransactionListComponent {}   // ❌ no standalone: true

// import { Component } from '@angular/core';
// import { CurrencyPipe, DatePipe } from '@angular/common';
//
// @Component({
//   selector: 'app-transaction-list',
//   standalone: true,
//   template: `
//     <h3>Transaction List</h3>
//     <ul>
//       @for (txn of transactions; track txn) {
//         <li>
//           {{ txn.date | date: 'mediumDate' }} -
//           {{ txn.amount | currency: 'INR' : 'symbol' : '1.2-2' }}
//         </li>
//       }
//     </ul>
//   `,
//   imports: [DatePipe, CurrencyPipe],
// })
// export class TransactionListComponent {
//   transactions = [
//     { date: '2026-08-01', amount: 2500 },
//     { date: '2026-08-02', amount: 12000 },
//     { date: '2026-08-03', amount: 500 },
//   ];
// }
