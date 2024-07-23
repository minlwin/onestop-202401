import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'profileImage'
})
export class ProfileImagePipe implements PipeTransform {

  transform(value: string | undefined): unknown {
    return value ? value : '/images/profile.png';
  }

}
