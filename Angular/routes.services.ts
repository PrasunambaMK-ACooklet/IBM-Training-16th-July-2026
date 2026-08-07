import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { TransactionsComponent } from './transactions.component';
import { TransferComponent } from './transfer.component';
import { LoginComponent } from './login.component';
import { TransactionListComponent } from './transaction-list.component';
import { AuthGuard } from './auth.gaurd';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'transactions', component: TransactionListComponent },
  { path: 'transfer', component: TransferComponent },
  { path: 'transactions', component: TransactionsComponent, canActivate: [AuthGuard] },
  { path: 'transfer', component: TransferComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
