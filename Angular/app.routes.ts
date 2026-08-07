// app.routes.ts
import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { TransactionListComponent } from './transaction-list.component';
import { TransferComponent } from './transfer.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'transactions', component: TransactionListComponent },
  { path: 'transfer', component: TransferComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];


// app.routes.ts
// import { Routes } from '@angular/router';
// import { DashboardComponent } from './dashboard.component';
// import { TransactionListComponent } from './transaction-list.component';
// import { TransferComponent } from './transfer.component';
//
// export const routes: Routes = [
//   { path: 'dashboard', component: DashboardComponent },
//   { path: 'transactions', component: TransactionListComponent },
//   { path: 'transfer', component: TransferComponent },
//   { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
// ];

// ============
// import { Routes } from '@angular/router';
// import { DashboardComponent } from './dashboard.component';
// import { TransactionsComponent } from './transactions.component';
// import { TransferComponent } from './transfer.component';
// import { LoginComponent } from './login.component';
// import { AccountDetailsComponent } from './account-details.component';
// import { MaskAccountDemoComponent } from './mask-account-demo.component';
//
//
// export const routes: Routes = [
//   { path: 'dashboard', component: DashboardComponent },
//   { path: 'transaction', component: TransactionsComponent },
//   { path: 'transfer', component: TransferComponent },
//   { path: 'login', component: LoginComponent },
//   { path: 'account', component: AccountDetailsComponent },
//   { path: 'mask-account', component: MaskAccountDemoComponent },
//   { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
// ];
//
//
