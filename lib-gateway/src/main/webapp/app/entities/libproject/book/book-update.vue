<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="libgatewayApp.libprojectBook.home.createOrEditLabel" data-cy="BookCreateUpdateHeading">Create or edit a Book</h2>
        <div>
          <div class="form-group" v-if="book.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="book.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="book-title"
              data-cy="title"
              :class="{ valid: !$v.book.title.$invalid, invalid: $v.book.title.$invalid }"
              v-model="$v.book.title.$model"
              required
            />
            <div v-if="$v.book.title.$anyDirty && $v.book.title.$invalid">
              <small class="form-text text-danger" v-if="!$v.book.title.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-author">Author</label>
            <input
              type="text"
              class="form-control"
              name="author"
              id="book-author"
              data-cy="author"
              :class="{ valid: !$v.book.author.$invalid, invalid: $v.book.author.$invalid }"
              v-model="$v.book.author.$model"
              required
            />
            <div v-if="$v.book.author.$anyDirty && $v.book.author.$invalid">
              <small class="form-text text-danger" v-if="!$v.book.author.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-fine_amount">Fine Amount</label>
            <input
              type="number"
              class="form-control"
              name="fine_amount"
              id="book-fine_amount"
              data-cy="fine_amount"
              :class="{ valid: !$v.book.fine_amount.$invalid, invalid: $v.book.fine_amount.$invalid }"
              v-model.number="$v.book.fine_amount.$model"
              required
            />
            <div v-if="$v.book.fine_amount.$anyDirty && $v.book.fine_amount.$invalid">
              <small class="form-text text-danger" v-if="!$v.book.fine_amount.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.book.fine_amount.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-publisher">Publisher</label>
            <input
              type="text"
              class="form-control"
              name="publisher"
              id="book-publisher"
              data-cy="publisher"
              :class="{ valid: !$v.book.publisher.$invalid, invalid: $v.book.publisher.$invalid }"
              v-model="$v.book.publisher.$model"
              required
            />
            <div v-if="$v.book.publisher.$anyDirty && $v.book.publisher.$invalid">
              <small class="form-text text-danger" v-if="!$v.book.publisher.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-quantity">Quantity</label>
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="book-quantity"
              data-cy="quantity"
              :class="{ valid: !$v.book.quantity.$invalid, invalid: $v.book.quantity.$invalid }"
              v-model.number="$v.book.quantity.$model"
              required
            />
            <div v-if="$v.book.quantity.$anyDirty && $v.book.quantity.$invalid">
              <small class="form-text text-danger" v-if="!$v.book.quantity.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.book.quantity.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-category">Category</label>
            <select class="form-control" id="book-category" data-cy="category" name="category" v-model="book.category">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="book.category && categoryOption.id === book.category.id ? book.category : categoryOption"
                v-for="categoryOption in categories"
                :key="categoryOption.id"
              >
                {{ categoryOption.name }}
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
            :disabled="$v.book.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./book-update.component.ts"></script>
