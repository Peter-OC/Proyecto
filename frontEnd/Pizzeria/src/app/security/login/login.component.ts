import { Component, OnInit } from '@angular/core';
import { LoginService } from '../security.service';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/common-services';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public miForm: FormGroup = new FormGroup({});
  txtButon = 'Log In';
  txtUsuario = '';
  txtPassword = '';

  constructor(public loginSrv: LoginService, private notify: NotificationService, private router: Router) { }

  contraseñaMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => control?.get('contraseñaValue')?.value === control?.get('contraseñaConfirm')?.value
      ? null : { 'mismatch': 'Son distintos' };
  }

  ngOnInit() {
    this.miForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(20), Validators.email]),
      contraseña: new FormGroup({
        contraseñaValue: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}$/)])
      }, this.contraseñaMatchValidator()),
    });
    this.cambiaTexto();
  }
  public getErrorMessage(name: string): string {
    if(this.miForm.pristine) return '';
    let cntr = this.miForm.get(name)
    let msg = '';
    if (cntr)
      for (let err in cntr.errors) {
        switch (err) {
          case 'required':
            msg += 'Es obligatorio. ';
            break;
          case 'minlength':
            msg += `Como mínimo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'maxlength':
            msg += `Como máximo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'pattern':
          case 'email':
            msg += 'El formato no es correcto. ';
            break;
          case 'min':
            msg += `El valor debe ser mayor o igual a ${cntr.errors[err].min}. `;
            break;
          case 'max':
            msg += `El valor debe ser inferior o igual a ${cntr.errors[err].max}. `;
            break;
          default:
            if (typeof cntr.errors[err] === 'string')
              msg += `${cntr.errors[err]}${cntr.errors[err].endsWith('.') ? '' : '.'} `;
            else if (typeof cntr.errors[err]?.message === 'string')
              msg += `${cntr.errors[err].message}${cntr.errors[err].message.endsWith('.') ? '' : '.'} `;
            break;
        }
      }
    return msg.trim();
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
