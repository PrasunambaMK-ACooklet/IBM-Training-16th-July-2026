import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <h2>Bank Registration</h2>
    <form [formGroup]="registerForm" (ngSubmit)="register()">
      <input formControlName="username" placeholder="Username" />
      @if (registerForm.get('username')?.invalid && registerForm.get('username')?.touched) {
        <div>Username is required</div>
      }

      <input type="email" formControlName="email" placeholder="Email" />
      @if (registerForm.get('email')?.invalid && registerForm.get('email')?.touched) {
        <div>Valid email is required</div>
      }

      <input type="password" formControlName="password" placeholder="Password" />
      @if (registerForm.get('password')?.invalid && registerForm.get('password')?.touched) {
        <div>Password must be at least 6 characters</div>
      }

      <input type="password" formControlName="confirmPassword" placeholder="Confirm Password" />
      @if (
        registerForm.get('confirmPassword')?.invalid && registerForm.get('confirmPassword')?.touched
      ) {
        <div>Passwords must match</div>
      }

      <button type="submit" [disabled]="registerForm.invalid">Register</button>
    </form>

    @if (error) {
      <p>&ndash;&gt;</p>
    }
    <!--    <p *ngIf="error">{{ error }}</p>&ndash;&gt;-->
  `,
})
export class RegisterComponent implements OnInit {
  registerForm: any;
  error = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.registerForm = this.fb.group(
      {
        username: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required],
      },
      { validators: this.passwordMatchValidator },
    );
  }

  passwordMatchValidator(form: any) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null
      : { mismatch: true };
  }

  register() {
    const { username, email, password } = this.registerForm.value;
    if (this.authService.register(username, email, password)) {
      this.router.navigate(['/login']);
    } else {
      this.error = 'Registration failed';
    }
  }
}
