import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { awards } from "src/app/resume-builder/model/awards";
import { certification } from "src/app/resume-builder/model/certification";
import { education } from "src/app/resume-builder/model/education";
import { experience } from "src/app/resume-builder/model/experience";
import { extracurricular } from "src/app/resume-builder/model/extracurricular";
import { hobbies } from "src/app/resume-builder/model/hobbies";
import { languages } from "src/app/resume-builder/model/language";
import { objectives } from "src/app/resume-builder/model/objectives";
import { personaldetails } from "src/app/resume-builder/model/personaldetails";
import { personalskills } from "src/app/resume-builder/model/personalskills";
import { projects } from "src/app/resume-builder/model/projects";
import { resume } from "src/app/resume-builder/model/resume";
import { skills } from "src/app/resume-builder/model/skills";
import { ResumeService } from "src/app/services/resume/resume.service";
import { faGithub, faLinkedinIn } from "@fortawesome/free-brands-svg-icons";
import {
  faExternalLink,
  faPhone,
  faEnvelope,
  faGlobe,
  faCalendar,
  faCalendarDay,
  faCalendarAlt,
  faLocation,
  faLocationPin,
  faLocationPinLock,
  faLocationArrow,
  faLocationDot,
  faSchool,
  faGraduationCap,
  faFlag,
  faBriefcase,
  faNotesMedical,
  faCertificate,
  faAward,
  faStar,
  faHeadSideCough,
  faLightbulb,
  faTicketSimple,
  faLanguage,
  faPuzzlePiece,
} from "@fortawesome/free-solid-svg-icons";
import jsPDF from "jspdf";
import html2canvas from "html2canvas";
import { SnackbarService } from "src/app/snackbar/snackbar.service";
import html2pdf from 'html2pdf.js';
import { faClipboard } from "@fortawesome/free-regular-svg-icons";


@Component({
  selector: "app-template1",
  templateUrl: "./template1.component.html",
  styleUrls: ["./template1.component.css"],
})
export class Template1Component implements OnInit {
  @ViewChild("page", { static: false }) page!: ElementRef;

  faLinkedin = faLinkedinIn;
  faGithub = faGithub;
  faWebsite = faExternalLink;
  faPhone = faPhone;
  faEmail = faEnvelope;
  faGlobe = faGlobe;
  faCalender = faCalendarDay;
  faLocation = faLocationDot;
  faEducation = faGraduationCap;
  faObjective = faFlag;
  faExperience = faBriefcase;
  faProject = faClipboard;
  faCertification = faCertificate;
  faAward = faAward;
  faSkill = faStar;
  faPersonalSkill = faLightbulb;
  faExtracurricular = faTicketSimple;
  faLanguage = faLanguage;
  faHobby = faPuzzlePiece;


  toggleEdit = false;
  resume!: resume;
  personalDetails: personaldetails = {
    fname: "",
    lname: "",
    email: "",
    gender: "",
    contact: "",
    city: "",
    state: "",
    dob: new Date(),
    linkedin_link: "",
    githublink: "",
    website: "",
  };

  objectives: objectives[] = [];
  educations: education[] = [];
  experiences: experience[] = [];
  projects: projects[] = [];
  certificates: certification[] = [];
  awards: awards[] = [];
  skills: skills[] = [];
  peronalSkills: personalskills[] = [];
  extracurriculars: extracurricular[] = [];
  languages: languages[] = [];
  hobbies: hobbies[] = [];
  resumeID!:string;
  constructor(private resumeService: ResumeService,
    private snackbarService:SnackbarService
    ) {}

  ngOnInit(): void {
    const savedData: string | null = sessionStorage.getItem("resumeID");
    if (savedData) {
      this.resumeID = JSON.parse(savedData);

      this.resumeService.getResume(this.resumeID).subscribe((data) => {
        this.resume = data;

        this.personalDetails = this.resume.personalDetails;
        this.objectives = this.resume.objectiveList;
        this.educations = this.resume.educationList;
        this.experiences = this.resume.experienceList;
        this.projects = this.resume.projectsList;
        this.certificates = this.resume.certificationList;
        this.awards = this.resume.awardsList;
        this.skills = this.resume.skillsList;
        this.peronalSkills = this.resume.personalSkillsList;
        this.extracurriculars = this.resume.extraCurricularList;
        this.languages = this.resume.languageList;
        this.hobbies = this.resume.hobbiesList;
      });
    }
  }
  
  toggle() {
    if (this.toggleEdit) 
    {
      this.toggleEdit = false;
      this.snackbarService.openMessageBar("PDF editing is OFF.")
    } 
    else 
    {
      this.toggleEdit = true;
      this.snackbarService.openMessageBar("PDF editing is ON.")
    }
  }

  print() {
    // window.print();
   
    const element = this.page.nativeElement;

    // Set the options for jsPDF
    const pdf = new jsPDF({
      unit: 'mm',
      format: 'a4',
      compress:true
    });

    // Set the options for html2pdf
    const options = {
      margin: [11,0,8,0],
      filename: 'resume.pdf',
      image: { type: 'jpeg', quality: 1 },
      html2canvas: { scale: 3 },
      jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' },
    };

    // Create the PDF using html2pdf
    html2pdf().from(element).set(options).toPdf().output('datauristring').then((pdfString:any) => {
      // Convert the data URI string to blob
      const blob = this.dataURItoBlob(pdfString);

      // Save the PDF with a file name
      this.saveAsPdf(blob, this.resumeID);
    });
  }

  dataURItoBlob(dataURI: string): Blob {
    const byteString = atob(dataURI.split(',')[1]);
    const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], { type: mimeString });
  }

  saveAsPdf(blob: Blob, filename: string) {
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = filename;
    a.click();
  }

  }

