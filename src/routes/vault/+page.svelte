<script module lang="ts">
    export type Entry = {
        feel: string
        because: string
        okbc: string
        feelbetter: string
        worked: string
    }
</script>

<script lang="ts">
    import { goto } from "$app/navigation";

    import { load } from "@tauri-apps/plugin-store";
    import { onMount } from "svelte";

    let entries: { date: Date, entry: Entry}[] = $state([])

    onMount(async () => {
        let store = await load('entries')

        entries = (await store.entries()).map(([key, value]) => {
            return {
                date: new Date(key),
                entry: value as Entry
            }
        }).sort((a, b) => { 
            return b.date.valueOf() - a.date.valueOf()
        })
    })


</script>

<button class="font-[lucon] inverted w-full h-10 my-10" onclick={() => goto("/")}>home</button>

<div class="grid grid-cols-2 gap-5">
{#each entries as entry }
    <button class="inverted h-fit" onclick={() => goto(`/vault/${entry.date.toISOString()}`)}>
        <p class="font-[lucon]">{entry.date.getMonth() + 1}/{entry.date.getDate()}/{entry.date.getFullYear()}</p>
        <p class="font-[lucon]">{entry.entry.feel}</p>
    </button>
{/each}
</div>