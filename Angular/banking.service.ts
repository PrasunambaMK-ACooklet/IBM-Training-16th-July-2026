import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class BankingService {
  private apiUrl = 'https://api.mybank.com'; // replace with your backend

  constructor(private http: HttpClient) {}

  transferFunds(data: { fromAccount: string; toAccount: string; amount: number }): Observable<any> {
    return this.http.post(`${this.apiUrl}/transfer`, data).pipe(
      catchError(err => {
        console.error('Transfer failed:', err);
        return throwError(() => new Error('Transfer request failed. Please try again.'));
      })
    );
  }

  getTransactions(accountId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/accounts/${accountId}/transactions`).pipe(
      catchError(err => {
        console.error('Fetching transactions failed:', err);
        return throwError(() => new Error('Could not load transactions.'));
      })
    );
  }
}
