import { Component } from '@angular/core';
import { MaskAccountPipe } from './mask-account.pipe';

@Component({
  selector: 'app-mask-account-demo',
  standalone: true,
  imports: [MaskAccountPipe], // <-- import the pipe here
  templateUrl: './mask-account-demo.component.html',
})
export class MaskAccountDemoComponent {
  accountNumber = '123456789012';
  anotherAccount = '987654321098';
}
