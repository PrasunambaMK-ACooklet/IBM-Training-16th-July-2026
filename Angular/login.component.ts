import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <h2>Bank Login</h2>
    <form [formGroup]="loginForm" (ngSubmit)="login()">
      <input formControlName="username" placeholder="Username" />
      @if (loginForm.get('username')?.invalid && loginForm.get('username')?.touched) {
        <div>Username is required</div>
      }

      <input type="password" formControlName="password" placeholder="Password" />
      @if (loginForm.get('password')?.invalid && loginForm.get('password')?.touched) {
        <div>Password must be at least 6 characters</div>
      }

      <button type="submit" [disabled]="loginForm.invalid">Login</button>
    </form>

    @if (error) {
      <p>{{ error }}</p>
    }
  `,
})
export class LoginComponent implements OnInit {
  loginForm: any;
  error = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  login() {
    const { username, password } = this.loginForm.value;
    if (this.authService.login(username, password)) {
      this.router.navigate(['/dashboard']);
    } else {
      this.error = 'Invalid credentials';
    }
  }
}
