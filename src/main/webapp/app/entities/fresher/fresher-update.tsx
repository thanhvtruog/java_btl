import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICenter } from 'app/shared/model/center.model';
import { getEntities as getCenters } from 'app/entities/center/center.reducer';
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { IFresher } from 'app/shared/model/fresher.model';
import { getEntity, updateEntity, createEntity, reset } from './fresher.reducer';

export const FresherUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const centers = useAppSelector(state => state.center.entities);
  const projects = useAppSelector(state => state.project.entities);
  const fresherEntity = useAppSelector(state => state.fresher.entity);
  const loading = useAppSelector(state => state.fresher.loading);
  const updating = useAppSelector(state => state.fresher.updating);
  const updateSuccess = useAppSelector(state => state.fresher.updateSuccess);

  const handleClose = () => {
    navigate('/fresher' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCenters({}));
    dispatch(getProjects({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...fresherEntity,
      ...values,
      center: centers.find(it => it.id.toString() === values.center.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...fresherEntity,
          center: fresherEntity?.center?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="javaBtlApp.fresher.home.createOrEditLabel" data-cy="FresherCreateUpdateHeading">
            Create or edit a Fresher
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="fresher-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Name"
                id="fresher-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Email"
                id="fresher-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Programming Language"
                id="fresher-programmingLanguage"
                name="programmingLanguage"
                data-cy="programmingLanguage"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Assignment 1 Score"
                id="fresher-assignment1Score"
                name="assignment1Score"
                data-cy="assignment1Score"
                type="text"
              />
              <ValidatedField
                label="Assignment 2 Score"
                id="fresher-assignment2Score"
                name="assignment2Score"
                data-cy="assignment2Score"
                type="text"
              />
              <ValidatedField
                label="Assignment 3 Score"
                id="fresher-assignment3Score"
                name="assignment3Score"
                data-cy="assignment3Score"
                type="text"
              />
              <ValidatedField label="Final Score" id="fresher-finalScore" name="finalScore" data-cy="finalScore" type="text" />
              <ValidatedField id="fresher-center" name="center" data-cy="center" label="Center" type="select">
                <option value="" key="0" />
                {centers
                  ? centers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fresher" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default FresherUpdate;
