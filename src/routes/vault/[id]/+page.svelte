<script lang="ts">
    import { page } from "$app/state";
    import { load } from "@tauri-apps/plugin-store";
    import { onMount } from "svelte";
    import type { Entry } from "../+page.svelte";
    import { goto } from "$app/navigation";

    let entry: undefined | Entry = $state()

    let key = page.params.id!;
    let date = new Date(key);

    onMount(async () => {
        let store = await load('entries')

        entry = await store.get(key)
    })
</script>

<button class="font-[lucon] inverted w-full h-10 my-10" onclick={() => goto("/")}>home</button>

<h1 class="font-[lucon] mb-30">{date.getMonth() + 1}/{date.getDate()}/{date.getFullYear()}</h1>

<div class="flex flex-col justify-center gap-5">
    <p class="font-[lucon]">I feel</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.feel}</p>
    <p class="font-[lucon]">because</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.because}</p>
    <p class="font-[lucon]">, but its ok because</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.okbc}</p>
    <p class="font-[lucon]">. to feel better, i did</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.feelbetter}</p>
    <p class="font-[lucon] inline pr-2">which {entry?.worked}</p>
</div>