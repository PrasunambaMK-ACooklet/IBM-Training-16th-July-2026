// import { Injectable } from '@angular/core';
//
// @Injectable({ providedIn: 'root' })
// export class AuthService {
//   private loggedIn = false;
//
//   login(username: string, password: string): boolean {
//     if (username === 'user' && password === 'pass123') {
//       this.loggedIn = true;
//       return true;
//     }
//     return false;
//   }
//
//   logout() {
//     this.loggedIn = false;
//   }
//
//   isLoggedIn(): boolean {
//     return this.loggedIn;
//   }
// }

//
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private users: { username: string; email: string; password: string }[] = [];

  // Register a new user
  register(username: string, email: string, password: string): boolean {
    // Check if email already exists
    const existingUser = this.users.find((u) => u.email === email);
    if (existingUser) {
      return false; // registration failed
    }

    this.users.push({ username, email, password });
    console.log('Registered users:', this.users);
    return true; // registration success
  }

  // Login with email + password
  login(email: string, password: string): boolean {
    const user = this.users.find((u) => u.email === email && u.password === password);
    return !!user;
  }

  // Simple helper to check if user exists
  isRegistered(email: string): boolean {
    return this.users.some((u) => u.email === email);
  }
}
