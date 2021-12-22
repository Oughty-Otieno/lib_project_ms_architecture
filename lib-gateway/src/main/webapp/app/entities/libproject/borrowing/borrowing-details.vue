<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="borrowing">
        <h2 class="jh-entity-heading" data-cy="borrowingDetailsHeading"><span>Borrowing</span> {{ borrowing.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Date Borrowed</span>
          </dt>
          <dd>
            <span>{{ borrowing.date_borrowed }}</span>
          </dd>
          <dt>
            <span>Due Date</span>
          </dt>
          <dd>
            <span>{{ borrowing.due_date }}</span>
          </dd>
          <dt>
            <span>Return Date</span>
          </dt>
          <dd>
            <span>{{ borrowing.return_date }}</span>
          </dd>
          <dt>
            <span>User Id</span>
          </dt>
          <dd>
            <span>{{ borrowing.user_id }}</span>
          </dd>
          <dt>
            <span>Book</span>
          </dt>
          <dd>
            <div v-if="borrowing.book">
              <router-link :to="{ name: 'BookView', params: { bookId: borrowing.book.id } }">{{ borrowing.book.title }}</router-link>
            </div>
          </dd>
          <dt>
                     <span>Fine Accrued</span>
                    </dt>
                    <dd>
                      <span>{{ borrowing.fine_accrued }}</span>
                    </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="borrowing.id"
          :to="{ name: 'BorrowingEdit', params: { borrowingId: borrowing.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./borrowing-details.component.ts"></script>
