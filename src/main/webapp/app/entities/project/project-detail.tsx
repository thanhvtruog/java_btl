import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './project.reducer';

export const ProjectDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const projectEntity = useAppSelector(state => state.project.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="projectDetailsHeading">Project</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{projectEntity.id}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{projectEntity.code}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{projectEntity.name}</dd>
          <dt>
            <span id="manager">Manager</span>
          </dt>
          <dd>{projectEntity.manager}</dd>
          <dt>
            <span id="startDate">Start Date</span>
          </dt>
          <dd>
            {projectEntity.startDate ? <TextFormat value={projectEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="endDate">End Date</span>
          </dt>
          <dd>{projectEntity.endDate ? <TextFormat value={projectEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="language">Language</span>
          </dt>
          <dd>{projectEntity.language}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{projectEntity.status}</dd>
          <dt>Freshers</dt>
          <dd>
            {projectEntity.freshers
              ? projectEntity.freshers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {projectEntity.freshers && i === projectEntity.freshers.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Center</dt>
          <dd>{projectEntity.center ? projectEntity.center.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/project" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/project/${projectEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProjectDetail;
