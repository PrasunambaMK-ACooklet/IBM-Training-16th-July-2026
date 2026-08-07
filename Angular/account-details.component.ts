import { Component , OnInit} from '@angular/core';
import { AccountService } from './account.service';
import { MaskAccountPipe } from './mask-account.pipe';

@Component({
  selector: 'app-account-details',
  standalone: true,
  imports: [MaskAccountPipe], // <-- import the pipe here
  // templateUrl: './account-details.component.html',
  template: `
    <h3>Account Details</h3>
    <ul>
      @for (account of accounts; track account) {
        <li>{{ account.name }} - {{ account.number | maskAccount }}</li>
      }
    </ul>
  `,
})
export class AccountDetailsComponent implements OnInit{
  accounts: any[] = [];

  constructor(private accountService: AccountService) {}

  ngOnInit() {
    this.accountService.getAccounts().subscribe((data) => {
      this.accounts = data as any[];
    });
  }
}
