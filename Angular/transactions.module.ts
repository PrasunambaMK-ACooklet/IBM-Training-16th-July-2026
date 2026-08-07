import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
// import { TransactionListComponent } from './transaction-list.component';
import { TransferComponent } from './transfer.component'; // standalone

@NgModule({
  // declarations: [TransactionListComponent], // ✅ only non-standalone here
  imports: [ReactiveFormsModule, TransferComponent], // ✅ standalone goes here
})
export class TransactionsModule {}
