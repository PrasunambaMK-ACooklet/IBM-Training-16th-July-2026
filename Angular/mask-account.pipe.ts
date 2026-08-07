import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'maskAccount',
  standalone: true
})
export class MaskAccountPipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return '';
    const visible = value.slice(-4);
    return 'XXXX-XXXX-' + visible;
  }
}
