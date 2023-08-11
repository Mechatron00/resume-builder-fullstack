import { Component, OnInit } from "@angular/core";
import {
  faCcPaypal,
  faGithub,
  faGooglePay,
  faInstagram,
  faLinkedin,
  faPaypal,
} from "@fortawesome/free-brands-svg-icons";
import { faEnvelope, faGlobe } from "@fortawesome/free-solid-svg-icons";
import { ContactService } from "./contact.service";
import { FormControl, NgForm, Validators } from "@angular/forms";

@Component({
  selector: "app-about",
  templateUrl: "./about.component.html",
  styleUrls: ["./about.component.css"],
})
export class AboutComponent implements OnInit {
  faInstagram = faInstagram;
  faLinkedin = faLinkedin;
  faGithub = faGithub;
  faEmail = faEnvelope;
  faPaypal = faCcPaypal;
  faGooglePay = faGooglePay;
  faWebsite = faGlobe;

  contactsData: contact = {
    fname: "",
    lname: "",
    email: "",
    message: "Type your message here, I'll get back to you as soon as possible.",
  };
  constructor(private contactService: ContactService) {}
  ngOnInit(): void {
   
  }
  successMessage:string='';

  send(contactForm: NgForm) {
    this.contactService.postMessage(this.contactsData).subscribe(
      (response) => {
        this.successMessage="Message sent successfully!";
        contactForm.reset({
          fname: "",
          lname: "",
          email: "",
          message: "Type your message here, I'll get back to you as soon as possible.",
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }
}

export interface contact {
  fname: string;
  lname: string;
  email: string;
  message: string;
}
