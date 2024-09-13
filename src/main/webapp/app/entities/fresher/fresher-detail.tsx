import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fresher.reducer';

export const FresherDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fresherEntity = useAppSelector(state => state.fresher.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fresherDetailsHeading">Fresher</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{fresherEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{fresherEntity.name}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{fresherEntity.email}</dd>
          <dt>
            <span id="programmingLanguage">Programming Language</span>
          </dt>
          <dd>{fresherEntity.programmingLanguage}</dd>
          <dt>
            <span id="assignment1Score">Assignment 1 Score</span>
          </dt>
          <dd>{fresherEntity.assignment1Score}</dd>
          <dt>
            <span id="assignment2Score">Assignment 2 Score</span>
          </dt>
          <dd>{fresherEntity.assignment2Score}</dd>
          <dt>
            <span id="assignment3Score">Assignment 3 Score</span>
          </dt>
          <dd>{fresherEntity.assignment3Score}</dd>
          <dt>
            <span id="finalScore">Final Score</span>
          </dt>
          <dd>{fresherEntity.finalScore}</dd>
          <dt>Center</dt>
          <dd>{fresherEntity.center ? fresherEntity.center.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/fresher" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fresher/${fresherEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default FresherDetail;
