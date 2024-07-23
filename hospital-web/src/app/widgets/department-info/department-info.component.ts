import { Component, input } from '@angular/core';

@Component({
  selector: 'app-department-info',
  templateUrl: './department-info.component.html',
  styles: ``
})
export class DepartmentInfoComponent {

  department = input<Department>()
}

export interface Department {
  readonly id:number
  readonly code:string
  readonly name:string
  readonly headName?:string
  readonly phone:string
  readonly email:string
}

export class DepartmentDto implements Department {

  constructor(private details:any) {
  }

  get id(): number {
    return this.details?.id || 0
  }

  get code(): string {
    return this.details?.code || ''
  }
  get name(): string {
    return this.details?.name || ''
  }
  get headName(): string | undefined {
    return this.details?.head?.name
  }
  get phone(): string {
    return this.details?.phone || ''
  }

  get email(): string {
    return this.details?.email || ''
  }


}
