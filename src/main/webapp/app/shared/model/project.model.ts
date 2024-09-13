import dayjs from 'dayjs';
import { IFresher } from 'app/shared/model/fresher.model';
import { ICenter } from 'app/shared/model/center.model';
import { ProjectStatus } from 'app/shared/model/enumerations/project-status.model';

export interface IProject {
  id?: number;
  code?: string;
  name?: string;
  manager?: string | null;
  startDate?: string | null;
  endDate?: string | null;
  language?: string | null;
  status?: ProjectStatus;
  freshers?: IFresher[] | null;
  center?: ICenter | null;
}

export const defaultValue: Readonly<IProject> = {};
