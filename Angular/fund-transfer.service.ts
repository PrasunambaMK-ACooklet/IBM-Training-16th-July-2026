import { Injectable } from '@angular/core';
import {NotificationService} from './notification.service';
@Injectable({
  providedIn: 'root'
})
export class FundTransferService {
  constructor(private notificationService: NotificationService) {}

  transferFunds(fromAccount: string, toAccount: string, amount: number): string {
    const result = `₹${amount} transferred from ${fromAccount} to ${toAccount}`;
    this.notificationService.notifyUser(result);
    return result;
  }
}
