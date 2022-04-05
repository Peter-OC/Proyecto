import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../common-services';
import { LoginService } from '../security';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    txtButon = 'Log In';
    txtUsuario = '';
    txtPassword = '';

    constructor(public loginSrv: LoginService, private notify: NotificationService, private router: Router) { }

    ngOnInit() {
      this.cambiaTexto();
    }

    logInOut() {
      if (this.loginSrv.isAutenticated) {
        this.loginSrv.logout();
        this.cambiaTexto();
      } else {
        this.loginSrv.login(this.txtUsuario, this.txtPassword).subscribe({
          next: data => {
            if (data) {
              this.cambiaTexto();
              this.setUser(this.txtUsuario);
            } else {
              this.notify.add('Usuario o contraseña invalida.');
            }
          },
          error: err => { this.notify.add(err.message); }
        });
      }
    }

    registrar() {
      this.router.navigateByUrl('/registro');
    }

    private cambiaTexto() {
      this.txtButon = this.loginSrv.isAutenticated ? 'Log Out' : 'Log In';
    }

    setUser(user: string) {
      localStorage.setItem("datos", JSON.stringify(user));
    }
  }



