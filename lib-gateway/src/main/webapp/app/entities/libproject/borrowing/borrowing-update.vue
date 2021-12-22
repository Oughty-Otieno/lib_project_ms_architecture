<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="libgatewayApp.libprojectBorrowing.home.createOrEditLabel" data-cy="BorrowingCreateUpdateHeading">
          Create or edit a Borrowing
        </h2>
        <div>
          <div class="form-group" v-if="borrowing.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="borrowing.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="borrowing-date_borrowed">Date Borrowed</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="borrowing-date_borrowed"
                  v-model="$v.borrowing.date_borrowed.$model"
                  name="date_borrowed"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="borrowing-date_borrowed"
                data-cy="date_borrowed"
                type="text"
                class="form-control"
                name="date_borrowed"
                :class="{ valid: !$v.borrowing.date_borrowed.$invalid, invalid: $v.borrowing.date_borrowed.$invalid }"
                v-model="$v.borrowing.date_borrowed.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="borrowing-due_date">Due Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="borrowing-due_date"
                  v-model="$v.borrowing.due_date.$model"
                  name="due_date"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="borrowing-due_date"
                data-cy="due_date"
                type="text"
                class="form-control"
                name="due_date"
                :class="{ valid: !$v.borrowing.due_date.$invalid, invalid: $v.borrowing.due_date.$invalid }"
                v-model="$v.borrowing.due_date.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="borrowing-return_date">Return Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="borrowing-return_date"
                  v-model="$v.borrowing.return_date.$model"
                  name="return_date"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="borrowing-return_date"
                data-cy="return_date"
                type="text"
                class="form-control"
                name="return_date"
                :class="{ valid: !$v.borrowing.return_date.$invalid, invalid: $v.borrowing.return_date.$invalid }"
                v-model="$v.borrowing.return_date.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="borrowing-user_id">User Id</label>
            <input
              type="number"
              class="form-control"
              name="user_id"
              id="borrowing-user_id"
              data-cy="user_id"
              :class="{ valid: !$v.borrowing.user_id.$invalid, invalid: $v.borrowing.user_id.$invalid }"
              v-model.number="$v.borrowing.user_id.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="borrowing-book">Book</label>
            <select class="form-control" id="borrowing-book" data-cy="book" name="book" v-model="borrowing.book">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="borrowing.book && bookOption.id === borrowing.book.id ? borrowing.book : bookOption"
                v-for="bookOption in books"
                :key="bookOption.id"
              >
                {{ bookOption.title }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.borrowing.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./borrowing-update.component.ts"></script>
