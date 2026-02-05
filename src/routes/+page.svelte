<script lang="ts">
  import { goto } from "$app/navigation";
</script>

<div class="h-screen flex flex-col justify-center gap-5">
  <p class="dialogue">it will pass</p>
  <p class="dialogue ml-7">do you really think so?</p>
  <p class="dialogue">no matter what, you will feel better eventually</p>
  <p class="dialogue ml-7">really?</p>
  <button class="dialogue w-fit bg-black text-white dark:bg-white dark:text-black" onclick={() => goto("/create")}>i promise</button>
</div>

<div class="absolute bottom-3 flex justify-center font-[lucon] w-full">
  <button onclick={() => goto("/vault")}>vault</button>
</div>

<style>
  .dialogue {
    font-family: lucon;
    padding: 5px;
  }
</style>